import axios from "axios";

export default axios.create({
    baseURL: "http://127.0.0.1:8080",
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Content-Type': 'application/json; charset = utf-8',
        'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS'
    }
});

// export default axios.create({
//     baseURL: "http://127.0.0.1:8080",
//     withCredentials: false,
//     headers: {
//       'Content-Type': 'application/json',
//       'Authorization': 'Bearer ' + localStorage.token,
//       'Access-Control-Allow-Origin': '*',
//       'Accept' : 'application/json, text/plain, */*',
//       'Access-Control-Allow-Methods' : 'GET, PUT, POST, DELETE, OPTIONS',
//       'Access-Control-Allow-Credentials' : true
//     }
// });