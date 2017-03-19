<!DOCTYPE html>
    <html>

        <head>
            <title>Review Hub</title>
        </head>

        <header>
            <h1>Create A New Movie</h1>
        </header>
        
        <body>
        ${success}
            <form action="ReviewServlet" method = post>             
	            <table>
	            	<tbody>
	            		<tr><td>Title:</td><td><input type="text" name="title" /></td></tr>            		
	            		<tr><td>Year:</td><td><input type="number" name="year" /></td></tr>
	            		<tr><td>Rank:</td><td><input type="number" name="rank" /></td></tr>
	            		<tr><td>Genre:</td><td><input type="text" name="genre" /></td></tr>
	            	</tbody>
	           	</table>
	           	<br/>
	        	<input type=submit name="addNewMovie" value="Create Movie" />
	        	<input type=submit value = "View All" name = "viewAll"/>
                <input type=submit value = "View All In Genre" name = "viewGenre"/>
			</form>
        </body>
        
    </html>