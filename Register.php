<?php
    $con = mysqli_connect("localhost", "id200594_p123456", "Quoc1234", "id200594_user");
    
    $name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["email"];
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, password, email) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, 'ssss', $name, $username, $email, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
    // debug
    //printf("%s %s.\n", $statement->password, $statement->email);
    // done debug
    //$statement->close();
    //$con->close();
?>