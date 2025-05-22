package com.bank.servlet;

import com.bank.dao.FundTransferDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
;

//@WebServlet("/RejectFundTransferServlet")
public class RejectFundTransferServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
	private FundTransferDAO ftDao = new FundTransferDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      int id = Integer.parseInt(req.getParameter("id"));
      HttpSession s = req.getSession();
      try {
        ftDao.updateStatus(id, "Failed");
        s.setAttribute("message", "Transfer #"+id+" rejected.");
      } catch (Exception e) {
        s.setAttribute("message", "Error: "+e.getMessage());
      }
      resp.sendRedirect(req.getContextPath()+"/ViewPendingFundTransfersServlet");
    }
}