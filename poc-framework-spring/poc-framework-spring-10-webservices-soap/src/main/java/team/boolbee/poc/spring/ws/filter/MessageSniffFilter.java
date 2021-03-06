package team.boolbee.poc.spring.ws.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageSniffFilter implements Filter {

	private static Log logger = LogFactory.getLog(MessageSniffFilter.class);

	public void init(FilterConfig arg0) throws ServletException {
		// Do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Wrap the request in order to read the inputstream multiple times
		MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
		BufferedReader reader = multiReadRequest.getReader();

		String text = null;
		StringBuffer buffer = new StringBuffer();
		while ((text = reader.readLine()) != null) {
			buffer.append(text);
		}
		logger.info(buffer.toString());
		chain.doFilter(multiReadRequest, response);
	}

	public void destroy() {
		// Do nothing
	}

	public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
		private ByteArrayOutputStream cachedBytes;

		public MultiReadHttpServletRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (cachedBytes == null)
				cacheInputStream();

			return new CachedServletInputStream();
		}

		@Override
		public BufferedReader getReader() throws IOException {
			return new BufferedReader(new InputStreamReader(getInputStream()));
		}

		private void cacheInputStream() throws IOException {
			/*
			 * Cache the inputstream in order to read it multiple times. For
			 * convenience, I use apache.commons IOUtils
			 */
			cachedBytes = new ByteArrayOutputStream();
			IOUtils.copy(super.getInputStream(), cachedBytes);
		}

		/* An inputstream which reads the cached request body */
		public class CachedServletInputStream extends ServletInputStream {
			private ByteArrayInputStream input;

			public CachedServletInputStream() {
				/* create a new input stream from the cached request body */
				input = new ByteArrayInputStream(cachedBytes.toByteArray());
			}

			@Override
			public int read() throws IOException {
				return input.read();
			}
		}
	}
}