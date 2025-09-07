<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF8">
    <title><s:text name="label.titulo.pagina.cadastro.compromisso"/></title>
    <link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">

    <div class="container">
        <s:form action="%{compromissoVo.idCompromisso == null ? 'salvarCompromissos' : 'atualizarCompromissos'}">

			<s:if test="hasActionErrors()">
    			<div class="alert alert-danger">
        			<s:actionerror/>
    		</div>
			</s:if>

            <div class="card mt-5">
                <div class="card-header">
                    <div class="row">
                        <div class="col-sm-5">
                            <s:url action="todosCompromissos" var="todos"/>
                            <a href="${todos}" class="btn btn-success" >
                                <s:text name="label.compromissos"/>
                            </a>
                        </div>
                        
                        <div class="col-sm">
                             <s:if test="compromissoVo.idCompromisso == null || compromissoVo.idCompromisso == ''">
                                <s:text name="label.novo.compromisso"/>
                            </s:if>
                            <s:else>
                                <s:text name="label.editar.compromisso"/>
                            </s:else>
                        </div>
                    </div>
                </div>
                
                <div class="card-body">
                
                	<div class="row align-items-center mt-3">
							<label for="idCompromisso" class="col-sm-2 col-form-label text-center"> Código:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="idCompromisso" name="compromissoVo.idCompromisso" value="%{compromissoVo.idCompromisso}" readonly="true"/>							
							</div>	
				 	</div>
                    <!-- Funcionário -->
                    <div class="row align-items-center mt-3">
                        <label for="idFuncionario" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.funcionario"/>:
                        </label>	

                        <div class="col-sm-4">
                            <s:select 
                                cssClass="form-control" 
                                id="idFuncionario" 
                                name="compromissoVo.idFuncionario"
                                list="funcionarios" 
                                listKey="rowid" 
                                listValue="rowid"
                                value="%{compromissoVo.idFuncionario}"
                                headerKey="" 
                                headerValue="%{getText('label.selecione.funcionario')}"
                            />							
                        </div>	
                    </div>
                    
                    <!-- Agenda -->
                    <div class="row align-items-center mt-3">
                        <label for="idAgenda" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.agenda"/>:
                        </label>	

                        <div class="col-sm-4">
                            <s:select 
                                cssClass="form-control" 
                                id="idAgenda" 
                                name="compromissoVo.idAgenda"
                                list="agendas" 
                                listKey="idAgenda" 
                                listValue="idAgenda"
                                value="%{compromissoVo.idAgenda}"
                                headerKey="" 
                                headerValue="%{getText('label.selecione.agenda')}"
                            />							
                        </div>	
                    </div>
                    
                    <!-- Data -->
                    <div class="row align-items-center mt-3">
                        <label for="data" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.data"/>:
                        </label>	

                        <div class="col-sm-3">
                            <s:textfield cssClass="form-control" id="data" name="compromissoVo.data" 
                                value="%{compromissoVo.data}" type="date"/>							
                        </div>	
                    </div>
                    
                    <!-- Horário -->
                    <div class="row align-items-center mt-3">
                        <label for="horario" class="col-sm-2 col-form-label text-center">
                            <s:text name="label.horario"/>:
                        </label>	

                        <div class="col-sm-3">
                            <s:textfield cssClass="form-control" id="horario" name="compromissoVo.horario" 
                                value="%{compromissoVo.horario}" type="time"/>							
                        </div>	
                    </div>
                </div>

                <div class="card-footer">
                    <div class="form-row">
                        <button class="btn btn-primary col-sm-4 offset-sm-1">
                            <s:text name="label.salvar"/>
                        </button>
                        <button type="button" class="btn btn-secondary col-sm-4 offset-sm-2" onclick="limparFormulario()">
                            <s:text name="label.limpar"/>
                        </button>
                    </div>
                </div>
            </div>
        </s:form>			
    </div>
    
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="js/form.js"></script>
</body>
</html>