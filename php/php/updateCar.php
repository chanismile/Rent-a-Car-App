<?php
   try {  
   require 'DB_Manager.php';
   $kilometer=$_REQUEST["kilometer"];
   $carId=$_REQUEST["carId"];
   

   $sql = "UPDATE `Cars` SET kilometer= '$kilometer' WHERE carId='$carId'";
   if ($conn->query($sql) === TRUE) {  
 	  echo "!!!!!"; 
   }  
   else 
   {   echo ":("; 
 } 
   
 }  catch(Exception $e) { 
 echo "Exception Error See Log....";
 error_log($e->getMessage() , 0); 
 } 
 $conn->close();
 ?> 