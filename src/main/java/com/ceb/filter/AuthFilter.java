package com.ceb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Protects all /admin/* URLs. Redirects unauthenticated users to /login.
 */
@WebFilter("/admin/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean ok = session != null && "ADMIN".equals(session.getAttribute("userRole"));

        if (ok) {
            chain.doFilter(request, response);
        } else {
            // Optionally remember where they tried to go:
            // req.getSession(true).setAttribute("redirectAfterLogin", req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
