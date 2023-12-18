
package controller;

import dao.PerfilDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;


@WebServlet(name = "GerenciarPerfil", 
        urlPatterns = {"/gerenciarPerfil"})
public class GerenciarPerfil extends HttpServlet {

  
   @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
        Perfil p = new Perfil();
        PerfilDAO pdao = new PerfilDAO();
        
        try {
            if(acao.equals("listar")){
            ArrayList<Perfil> perfis = new ArrayList<>();
            perfis = pdao.getLista();
            RequestDispatcher dispatcher =
                    getServletContext().
                       getRequestDispatcher("/listarPerfis.jsp");
            request.setAttribute("perfis", perfis);
            dispatcher.forward(request, response);
            }else if(acao.equals("alterar")){
            
            }else if(acao.equals("ativar")){
            
            }else if(acao.equals("desativar")){
            
            }else{
                response.sendRedirect("index.jsp");
            }
        
           
       } catch (SQLException e) {
           mensagem = "Erro: " + e.getMessage();
           e.printStackTrace();
       }
          
        out.println(
            "<script type='text/javascript'>" +
            "alert('" + mensagem + "');" +
            "location.href='gerenciarPerfil?acao=listar';" +
            "</script>"
        );
        
        
        
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idPerfil = request.getParameter("idPerfil");
        String nome = request.getParameter("nome");
        String dataCadastro = request.getParameter("dataCadastro");
        String status = request.getParameter("status");
        String mensagem = "";
        String msg = "";
        
        Perfil p = new Perfil();
        PerfilDAO pdao = new PerfilDAO();
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        if(!idPerfil.isEmpty()){
            p.setIdPerfil(Integer.parseInt(idPerfil));
        }
        
        HttpSession sessao = request.getSession();
        
        if(nome.isEmpty() || nome.equals("")){
            sessao.setAttribute("msg","Informe o nome do Perfil!");
            exibirMensagem(request, response);
        }else{
            p.setNome(nome);
        }
        
        if(dataCadastro.isEmpty() || dataCadastro.equals("")){
            sessao.setAttribute("msg","Informe a data de cadastro!");
            exibirMensagem(request, response);
        }else{
            try {
                p.setDataCadastro(df.parse(dataCadastro));
            } catch (ParseException e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }
        }
        
        if(status.isEmpty() || status.equals("")){
            sessao.setAttribute("msg", "Informe o status!");
            exibirMensagem(request, response);
            
        }else{
            p.setStatus(Integer.parseInt(status));
            
        }
        
        try {
            if( pdao.gravar(p)){
                mensagem = "Perfil salvo na base de dados!";
            }else{
                mensagem = 
                 "Falha ao salvar o perfil na base de dados!";
            }
           
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
         out.println(
            "<script type='text/javascript'>" +
            "alert('" + mensagem + "');" +
            "location.href='gerenciarPerfil?acao=listar';" +
            "</script>"
        );
        
    }
    
      private void exibirMensagem(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
        RequestDispatcher dispatcher =
        getServletContext().
                getRequestDispatcher("/cadastrarPerfil.jsp");
        dispatcher.forward(request, response);
        
        
    }
    
  

}
