<?php
   try {  
   require 'DB_Manager.php';
   
   $kilometerEndValue=$_REQUEST["kilometerEndValue"];
   $orderId=$_REQUEST["orderId"];
   $orderKind = $_REQUEST["orderKind"]; 
   $fuelFilling = $_REQUEST["fuelFilling"]; 
   $amountFilling = $_REQUEST["amountFilling"]; 
   $orderPayment = $_REQUEST["orderPayment"]; 

   $sql = "UPDATE `Order` SET kilometerEndValue= '$kilometerEndValue',orderKind ='$orderKind',fuelFilling='$fuelFilling',amountFilling='$amountFilling',orderPayment='$orderPayment' WHERE orderId='$orderId'";
   
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