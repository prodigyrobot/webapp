
<html>
<body>
    <H1>Add User:</H1>
    <BR>
    
    <form action="adduser" method="POST">
       Name: <input type="text" name="name"><br />
       <input type="submit" value="Submit" />
    </form>
    
     <BR>
    <H3><a href="<%=request.getContextPath()%>/users">Users</a></H3>
</body>
</html>