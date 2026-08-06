import axios from "axios";

const API = axios.create({
    baseURL: process.env.REACT_APP_API_URL || "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
    },
});

API.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");

    if (token && !config.url.startsWith("/auth")) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

export default API;