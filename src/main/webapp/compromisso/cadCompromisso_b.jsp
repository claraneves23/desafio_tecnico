<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF8">
    <title><s:text name="label.titulo.pagina.consulta.compromisso"/></title>
    <link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">	
    <div class="container">
    <s:if test="hasActionErrors()">
    	<div class="alert alert-danger">
        	<s:actionerror/>
   	 </div>
	</s:if>
        <div class="row">
            <table class="table table-light table-striped align-middle">
                <thead>
                    <tr>
                    	<th><s:text name="label.compromisso"/></th>
                        <th><s:text name="label.funcionario"/></th>
                        <th><s:text name="label.agenda"/></th>
                        <th><s:text name="label.data"/></th>
                        <th><s:text name="label.horario"/></th>
                        <th class="text-end mt-5"><s:text name="label.acao"/></th>
                    </tr>
                </thead>
                
                <tbody>
                    <s:iterator value="compromissos" >
                        <tr>
                        	<td>${idCompromisso}</td>
                            <td>${idFuncionario}</td>
                            <td>${idAgenda}</td>
                            <td>${data}</td>
                            <td>${horario}</td>
                            <td class="text-end">
                                <s:url action="editarCompromissos" var="editar">
                                    <s:param name="compromissoVo.idCompromisso" value="idCompromisso"></s:param>
                                </s:url>

                                <a href="${editar}" class="btn btn-warning text-white">
                                    <s:text name="label.editar"/>
                                </a>

                               	<a href="#" class="btn btn-danger" 
   									onclick="prepararExclusao('${idCompromisso}', 'compromisso')">
    								<s:text name="label.excluir"/>
								</a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
                
                <tfoot class="table-secondary">
                    <tr>
                        <td colspan="6">
                            <s:url action="novoCompromissos" var="novo"/>
                            
                            <a href="${novo}" class="btn btn-success">
                                <s:text name="label.novo"/>
                            </a>
                        </td>
                    </tr>
                </tfoot>                
            </table>
        </div>
    </div>
    
<div class="modal fade" id="confirmarExclusao" 
        data-bs-backdrop="static" data-bs-keyboard="false"
        tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title"><s:text name="label.modal.titulo.compromisso"/></h5>
            
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          
          <div class="modal-body">
            <span><s:text name="label.modal.corpo.compromisso"/></span>
          </div>
          
         <div class="modal-footer">
    		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
        		<s:text name="label.nao"/>
    		</button>
    
    		<a id="linkExclusao" href="#" class="btn btn-primary" style="width: 75px;">
        		<s:text name="label.sim"/>
    		</a>						
		</div>
        </div>		    
      </div>
 </div>  
    
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="js/exclusao-modal.js"></script>
    
</body>
</html>