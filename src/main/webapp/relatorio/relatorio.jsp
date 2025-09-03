<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Relatório de Compromissos</title>
    <link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Relatório de Compromissos por Período</h3>
            </div>
            <div class="card-body">
                <!-- ÚNICO Formulário -->
                <s:form id="relatorioForm" action="gerarRelatorios" method="POST">
                    <s:if test="hasActionErrors()">
                        <div class="alert alert-danger">
                            <s:actionerror/>
                        </div>
                    </s:if>
                    
                    <s:if test="hasActionMessages()">
                        <div class="alert alert-info">
                            <s:actionmessage/>
                        </div>
                    </s:if>
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label">Data Inicial:</label>
                            <s:textfield name="filtro.dataInicial" type="date" cssClass="form-control" required="true"/>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Data Final:</label>
                            <s:textfield name="filtro.dataFinal" type="date" cssClass="form-control" required="true"/>
                        </div>
                    </div>
                    
                    <!-- Botões lado a lado -->
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <button type="button" onclick="submitForm('gerarRelatorios')" class="btn btn-primary w-100">
                                Gerar Relatório HTML
                            </button>
                        </div>
                        <div class="col-md-6">
                            <button type="button" onclick="submitForm('exportarRelatorios')" class="btn btn-success w-100">
                                Exportar para Excel
                            </button>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>
    </div>

    <script src="js/form.js"></script>
</body>
</html>