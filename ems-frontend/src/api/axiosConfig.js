import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
    },
});

API.interceptors.request.use((config) => {

    const token = localStorage.getItem("token");

    // Don't attach token for public endpoints
    if (
        token &&
        !config.url.startsWith("/auth")
    ) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

export default API;