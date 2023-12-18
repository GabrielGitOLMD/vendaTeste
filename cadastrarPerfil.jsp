
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1,shink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>Projeto ETB</title>
       </head>
    <body>
        <div id="container">
            
            <div id="header">
                <jsp:include page="template/banner.jsp"></jsp:include>
                
            </div><!-- fim da div header -->
            <div id="menu">
            <jsp:include page="template/menu.jsp"></jsp:include>
                
            </div> <!-- fim da div menu -->
            <div id="conteudo" class="bg-background" >
                <%
                    HttpSession sessao = request.getSession();
                    if(session.getAttribute("msg") != null){
                        String msg = (String) session.getAttribute("msg");
                        if(msg != null){
                  
                 %>
                 <div class="alert alert-warning alert-dismissible fade show"
                      role="alert"><%= msg %>
                     <button type="button" class= "close"
                             data-dismiss="alert" aria-label="Close">
                         <span aria-hidden="true">&times;</span>
                     
                     </button>
                 
                 </div>
                           
                <%
                        }
                    }
                
                
                %>
                <div class="container">
                    <form action="gerenciarPerfil" method="post">
                        <h3 class="text-center">Cadastro de Perfil</h3>
                        <input type="hidden" name="idPerfil"
                               value="">
                        
                        <div class="form-group row mt-5 offset-md-2">
                            <label class="col-md-3">Nome</label>
                            <div class="col-md-5">
                                <input type="text" name="nome" 
                                       class= "form-control" value="">
                            </div>
                        </div>
                        <div class="form-group row offset-md-2">
                            <label class="col-md-3">Data de Cadastro</label>
                            <div class="col-md-5">
                                <input type="date" name="dataCadastro"
                                       class="form-control" value="">
                            </div>
                            
                        </div>
                        
                        <div class="form-group row offset-md-2">
                            <label class="col-md-3">Status</label>
                            <div class="col-md-5">
                                <select class="form-control-sm" name="status">
                                    <option value="">Escolha uma opção</option>
                                    <option value="1"
                                    <c:if test="${perfil.status == 1}">
                                        selected=""
                                        </c:if>>Ativado
                                    </option>
                                    <option value="0"
                                    <c:if test="${perfil.status == 0}">
                                        selected=""
                                        </c:if>>Desativado</option>
                                </select>
                            </div>
                            <div class="d-md-flex justify-content-md-end mt-5">
                                <button class="btn btn-primary btn-md mr-3">Gravar</button>
                                <a href="gerenciarPerfil?acao=listar"
                                   class="btn btn-warning btn-md"
                                   role="button">Voltar
                                </a>
                                
                            </div>
                            
                        </div>
                        
                    </form>
                    
                </div>
         
                
                
            </div> <!-- fim da div conteúdo -->
            
        </div> <!-- fim da div container -->
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
        
    </body>
</html>

