<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF8">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
		
		<div class="container">
		
		<s:if test="hasActionErrors()">
					<div class="alert alert-danger mt-3">
						<s:actionerror/>
					</div>
		</s:if>
		
			<s:form action="%{funcionarioVo.rowid == null ? 'salvarFuncionarios' : 'atualizarFuncionarios'}">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosFuncionarios" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Funcionários</a>
							</div>
							
							<div class="col-sm">
								 <s:if test="funcionarioVo.rowid == null || funcionarioVo.rowid == ''">
        							Novo Funcionário
    							</s:if>
    							<s:else>
        							Editar Funcionário
    							</s:else>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								Código:
							</label>	

							<div class="col-sm-2">
								<input type="text" class="form-control" id="id" name="funcionarioVo.rowid" value="<s:property value='funcionarioVo.rowid'/>" readonly="readonly"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-1 col-form-label text-center">
								Nome:
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="funcionarioVo.nome"/>							
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