<?php
   try {  
   require 'DB_Manager.php'; 
   
   $branchNo = $_REQUEST["branchNo"]; 
   $model = $_REQUEST["model"]; 
   $kilometer = $_REQUEST["kilometer"]; 
   $carId = $_REQUEST["carId"]; 
   $sql = "INSERT INTO `Car`(  `branchNo`,`model`,`kilometer`,`carId`)
   VALUES ( '$branchNo', '$model', '$kilometer', '$carId')";
   if ($conn->query($sql) === TRUE) {  
   $last_id = $conn->insert_id;  
   echo $last_id; 
   }  
   else 
   {   echo "Error: " . $sql . "\n" . $conn->error; 
 } 
 }  catch(Exception $e) { 
 echo "Exception Error See Log....";
 error_log($e->getMessage() , 0); 
 } 
 $conn->close();
 ?> 