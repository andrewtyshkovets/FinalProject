<!DOCTYPE html>
<html>
<head>
    <title>Add product by code</title>
    <link href="CSS/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Add product
    </div>
    <form method="post" action="/addProductC">
        <table>
            <tr>
                <td>
                  Code of product
                </td>
                <td>
                   <input class="input-field" type="number" id="code" name="productCode">
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