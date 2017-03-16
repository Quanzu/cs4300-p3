<!DOCTYPE html>
    <html>

        <head>
            <title>Review Hub</title>
        </head>

        <header>
            <h1>Viewing ALL</h1>
        </header>
        
        <body>
            <form action="ReviewServlet" method = post>
                <input type=submit value = "View All" name = viewAll/>
                <input type=submit value = "View All In Genre" name = viewGenre/>
                <input type=submit value = "Submit A New Movie" name = newMovie/>
            </form>
            
            <table>
           		<#list movies as movies>
        			<tr><td>${movies.name}</td></tr>  				
           		</#list>
           	</table>
        </body>
        
    </html>