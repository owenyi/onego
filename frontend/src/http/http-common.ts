import store from "@/store";
import axios from "axios";

export default axios.create({
    // baseURL: "http://127.0.0.1:8080",
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Content-Type': 'application/json; charset = utf-8',
        'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
        'Context-Type': 'multipart/form-data',
        'Authorization': store.state.accessToken
    }
});
