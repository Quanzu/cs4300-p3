<!DOCTYPE html>
    <html>

        <head>
            <title>Review Hub</title>
        </head>

        <header>
            <h1>Genres Available for Search:</h1> 
        </header>
        
        <body>
			<form action="ReviewServlet" method="post">
				<table>
					<#list genres as genres>
						<tr><td>${genres}</td><td><input type=radio name=${genres} value=${genres} /></td></tr>	
					</#list>
				</table>
				<input type=submit name=searchGenre value="Search" />
			</form>
        </body>
                
    </html>