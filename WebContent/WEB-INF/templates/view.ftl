<!DOCTYPE html>
    <html>

        <head>
            <title>Review Hub</title>
        </head>

        <header>
            <h1>Viewing: ${category}</h1>
        </header>
        
        <body>
            <form action="ReviewServlet" method = post>
                <input type=submit value = "View All" name = "viewAll"/>
                <input type=submit value = "View All In Genre" name = "viewGenre"/>
                <input type=submit value = "Submit A New Movie" name = "newMovie"/>
            </form>
            
            <table>
            	<tr><td>ID</td><td>Name</td><td>Year</td><td>Rating</td><td>Genre</td></tr>
           		<#list movies as movies>
        			<tr><td>${movies.id}</td><td>${movies.name}</td><td>${movies.year}</td><td>${movies.rank}</td><td>${movies.genre}</td></tr>  				
           		</#list>
           	</table>
        </body>
        
    </html>