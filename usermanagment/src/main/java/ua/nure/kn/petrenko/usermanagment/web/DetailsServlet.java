package main.java.ua.nure.kn.vitalii.petrenko.usermanagment.web;


public class DetailsServlet extends AddServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            req.getRequestDispatcher("/browse").forward(req, resp);
            return;
        }
        showPage(req,resp);
    }
    
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/details.jsp").forward(req, resp);
        
    }
}