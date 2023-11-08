package dev;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/voyages")
public class VoyageServlet extends HttpServlet {

    public VoyageServlet() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> voyages = new ArrayList<>();

        // "mysql-bdd" ici représente le nom (--name) du conteneur de la base de données MySQL
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-bdd:3306/voyages_bdd", "root", "root");
             PreparedStatement ps = connection.prepareStatement("select * from voyages");
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                String nom = rs.getString("nom");
                voyages.add(nom.toUpperCase());
            }

            resp.getWriter().write(voyages.toString());


        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");

        // "mysql-bdd" ici représente le nom (--name) du conteneur de la base de données MySQL
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-bdd:3306/voyages_bdd", "root", "root");
             PreparedStatement ps = connection.prepareStatement("insert into voyages(nom) values(?)")) {

            ps.setString(1, nom);

            int nbLignesInserees = ps.executeUpdate();


            resp.getWriter().write(nbLignesInserees + " lignes insérées");


        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }


    }
}
