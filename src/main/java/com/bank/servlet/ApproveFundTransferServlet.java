package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.FundTransferDAO;
import com.bank.model.FundTransfer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

//@WebServlet("/ApproveFundTransferServlet")
public class ApproveFundTransferServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
	private FundTransferDAO ftDao = new FundTransferDAO();
    private AccountDAO accDao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      int id = Integer.parseInt(req.getParameter("id"));
      HttpSession s = req.getSession();
      try {
        FundTransfer ft = ftDao.findById(id);
        String from = ft.getCustomerId();
        String to   = ft.getBeneficiaryCustomerId();
        BigDecimal amt = ft.getAmount();

        // debit & credit
        BigDecimal b1 = accDao.getBalance(from).subtract(amt);
        BigDecimal b2 = accDao.getBalance(to).add(amt);
        accDao.updateBalance(from, b1);
        accDao.updateBalance(to, b2);

        ftDao.updateStatus(id, "Completed");
        s.setAttribute("message", "Transfer #"+id+" approved.");
      } catch (Exception e) {
        s.setAttribute("message", "Error: "+e.getMessage());
      }
      resp.sendRedirect(req.getContextPath()+"/ViewPendingFundTransfersServlet");
    }
}