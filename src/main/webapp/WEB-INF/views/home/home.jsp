<%@ include file="/WEB-INF/views/includes/includes.jsp" %>
<!-- <!DOCTYPE html> -->
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=request.getContextPath() %>/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" 
    integrity="sha384-7ynz3n3tAGNUYFZD3cWe5PDcE36xj85vyFkawcF6tIwxvIecqKvfwLiaFdizhPpN" crossorigin="anonymous">
    
    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,600;1,600&display=swap" rel="stylesheet">
    
    <style type="text/css">
		<%@ include file="/resources/vendor/bootstrap/css/bootstrap.min.css" %>
		<%@ include file="/resources/vendor/metisMenu/metisMenu.min.css" %>
		<%@ include file="/resources/dist/css/sb-admin-2.css" %>
		<%@ include file="/resources/vendor/font-awesome/css/font-awesome.min.css" %>
		<%@ include file="/resources/dist/css/style.css" %>
 	</style>

</head>

<body>

    <div id="wrapper">
    

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            
            <%@ include file="/WEB-INF/views/menu_top/topMenu.jsp" %>
			
            <%@ include file="/WEB-INF/views/menu_left/leftMenu.jsp" %>
            <!-- /.navbar-static-side -->
            
        </nav>
        
			<!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">		
				 <div class="row header">
                    <div class="col-lg-12">
                        <div class="text_box">
                        	<h1><fmt:message code="welcome.titre" /></h1>
                        	<p><fmt:message code="welcome.titre.2" /></p>
                        </div>
                    </div>
<!--                     /.col-lg-12 -->
                </div>
<!--                 /.row -->


				<div class="row desc">
                    <div class="col-lg-12">
                        <div class="">
                        	<h2><fmt:message code="welcome.titre.3" /></h2>
                        	
                        	<div class="row_style">
                       			<div class="desc_col_4">
	                       			<h3><fmt:message code="welcome.article.h3" /></h3>
	                       			<p><fmt:message code="welcome.article.p" /></p>
                       			</div>
                        		<div class="desc_col_4">
                        			<h3><fmt:message code="welcome.client.h3" /></h3>
                        			<p><fmt:message code="welcome.client.p" /></p>
                        		</div>
                        		<div class="desc_col_4">
                        			<h3><fmt:message code="welcome.fournisseur.h3" /></h3>
                        			<p><fmt:message code="welcome.fournisseur.p" /></p>
                        		</div>
                        		<div class="desc_col_4">
                        			<h3><fmt:message code="welcome.commande.h3" /></h3>
                        			<p><fmt:message code="welcome.commande.p" /></p>
                        		</div>
                        	</div>
                        	
                        	
                        	
                        	<div class="row_style">
                        		<div class="desc_col_3">
                        			<h3><fmt:message code="welcome.securite.h3" /></h3>
                        			<p><fmt:message code="welcome.securite.p" /></p>
                        		</div>
                        		<div class="desc_col_3">
                        			<h3><fmt:message code="welcome.flickr.h3" /></h3>
                        			<p><fmt:message code="welcome.flickr.p" /></p>
                        		</div>
                        		<div class="desc_col_3">
                        			<h3><fmt:message code="welcome.i18n.h3" /></h3>
                        			<p><fmt:message code="welcome.i18n.p" /></p>
                        		</div>
                        		<div class="desc_col_3">
                        			<h3><fmt:message code="welcome.export.titre" /></h3>
                        			<p><fmt:message code="welcome.export.p" /></p>
                        		</div>
                        	</div>
                        	
                        	
                        </div>
                    </div>
<!--                     /.col-lg-12 -->
				
					<div class="footer">
						<p><fmt:message code="welcome.develop" /></p>
						<p><strong><fmt:message code="welcome.develop.by" /></strong></p>
					</div>
					
                </div>
<!--                 /.row -->
				
            </div>
<!--             /.container-fluid -->
        </div>
<!--         /#page-wrapper -->
		
		
		
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<%=request.getContextPath() %>/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=request.getContextPath() %>/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>
