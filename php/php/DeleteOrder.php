<?php
  try {  
   require 'DB_Manager.php'; 
   
   $orderId = $_REQUEST["orderId"]; 
   
$sql = "DELETE FROM `Order` WHERE orderId=$orderId"; 
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