/**
 * 
 */
let form = document.getElementById('register').addEventListener('sumbit', register);

async function register(e){
    e.preventDefault();
    let username = document.getElementById('username').Value;
    let password = document.getElementById('password').Value;
    let firstname = document.getElementById('firstname').Value;
    let lastname = document.getElementById('lastname').Value;
    let email = document.getElementById('email').Value;

    let user = {
        firstname,
        lastname,
        username,
        email,
        password
    };

    console.log(user);

    try{
        let req = await fetch('http://localhost:8080/ERS/api/register',{
            method: 'POST',
            headers:{
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res);
    }catch(e){
        alert("Email Or Username Already In Use");
        return;
    }

    location.href = 'resources/html/employeehome.html'
}