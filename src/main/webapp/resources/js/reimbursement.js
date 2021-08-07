/**
 * 
 */
 let form = document.getElementById('newRequest').addEventListener('sumbit', addReimbursement);

 async function addReimbursement(e){
     e.preventDefault();

     let select = document.getElementById('type');
     let result = select.options[select.selectedIndex].value;
     let amount = document.getElementById('amount').Value;
     let description = document.getElementById('description').Value;
     let today = new Date().toLocaleDateString();
 
     let user = {
         result,
         amount,
         description,
         today
     };
 
     console.log(user);
 
         let req = await fetch('http://localhost:8080/ERS/api/addrequest',{
             method: 'POST',
             headers:{
                 'Content-Type' : 'application/json'
             },
             body: JSON.stringify(user)
         });
         let res = await req.json();
         console.log(res);

     
 
     location.href = 'resources/html/employeehome.html'
 }