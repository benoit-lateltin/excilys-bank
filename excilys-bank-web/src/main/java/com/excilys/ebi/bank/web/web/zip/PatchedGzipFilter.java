package com.excilys.ebi.bank.web.web.zip;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.constructs.web.GenericResponseWrapper;
import net.sf.ehcache.constructs.web.ResponseUtil;
import net.sf.ehcache.constructs.web.filter.GzipFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://jira.terracotta.org/jira/browse/EHCWEB-1
 * 
 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
 */
public class PatchedGzipFilter extends GzipFilter {

	private static final Logger LOG = LoggerFactory.getLogger(GzipFilter.class);
	private static final String VARY_HEADER_PARAM = "varyHeader";
	private static final String RETURN_ON_NOT_OK_PARAM = "returnOnNonOK";

	private boolean setVaryHeader;
	private boolean returnOnNonOk = true;

	protected void doInit(FilterConfig filterConfig) throws Exception {
		String varyParam = filterConfig.getInitParameter(VARY_HEADER_PARAM);
		if (varyParam != null) {
			setVaryHeader = Boolean.valueOf(varyParam);
		}

		String returnOnNotOkParam = filterConfig.getInitParameter(RETURN_ON_NOT_OK_PARAM);
		if (returnOnNotOkParam != null) {
			returnOnNonOk = Boolean.valueOf(returnOnNotOkParam);
		}
	}

	protected void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws Exception {
		if (!isIncluded(request) && acceptsEncoding(request, "gzip") && !response.isCommitted()) {
			// Client accepts zipped content
			if (LOG.isDebugEnabled()) {
				LOG.debug(request.getRequestURL() + ". Writing with gzip compression");
			}

			// Create a gzip stream
			final ByteArrayOutputStream compressed = new ByteArrayOutputStream();
			final GZIPOutputStream gzout = new GZIPOutputStream(compressed);

			// Handle the request
			final GenericResponseWrapper wrapper = new GenericResponseWrapper(response, gzout);
			wrapper.setDisableFlushBuffer(true);
			chain.doFilter(request, wrapper);
			wrapper.flush();

			gzout.close();

			// return on error or redirect code, because response is already
			// committed
			int statusCode = wrapper.getStatus();
			// if (statusCode != HttpServletResponse.SC_OK) {
			if (returnOnNonOk && statusCode != HttpServletResponse.SC_OK) {
				return;
			}

			// Saneness checks
			byte[] compressedBytes = compressed.toByteArray();
			boolean shouldGzippedBodyBeZero = ResponseUtil.shouldGzippedBodyBeZero(compressedBytes, request);
			boolean shouldBodyBeZero = ResponseUtil.shouldBodyBeZero(request, wrapper.getStatus());
			if (shouldGzippedBodyBeZero || shouldBodyBeZero) {
				// No reason to add GZIP headers or write body if no content was
				// written or status code specifies no content
				response.setContentLength(0);
				return;
			}

			// Write the zipped body
			ResponseUtil.addGzipHeader(response);

			// Only write out header Vary as needed
			if (setVaryHeader) {
				ResponseUtil.addVaryAcceptEncoding(wrapper);
			}

			response.setContentLength(compressedBytes.length);

			response.getOutputStream().write(compressedBytes);
		} else {
			// Client does not accept zipped content - don't bother zipping
			if (LOG.isDebugEnabled()) {
				LOG.debug(request.getRequestURL() + ". Writing without gzip compression because the request does not accept gzip.");
			}
			chain.doFilter(request, response);
		}
	}

	/**
	 * Checks if the request uri is an include. These cannot be gzipped.
	 */
	private boolean isIncluded(final HttpServletRequest request) {
		final String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		final boolean includeRequest = !(uri == null);

		if (includeRequest && LOG.isDebugEnabled()) {
			LOG.debug(request.getRequestURL() + " resulted in an include request. This is unusable, because"
					+ "the response will be assembled into the overrall response. Not gzipping.");
		}
		return includeRequest;
	}
}
