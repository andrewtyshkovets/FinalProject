<!DOCTYPE html>
<html>
<head>
    <title>Add product by name</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add product
    </div>
    <form method="post" action="/addProductN">
        <table>
            <tr>
                <td>
                  Name of product
                </td>
                <td>
                   <input class="input-field" type="text" id="name" name="productName">
                </td>
            </tr> 
            <tr>
                <td>
                    <label for="amount">Amount</label>
                </td>
                <td>
                  <input class="input-field" type="number" id="amount" name="amount">  
                </td>
            </tr>        
        </table>
        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>