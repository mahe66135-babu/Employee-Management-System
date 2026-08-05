import { useState } from "react";
import { jwtDecode } from "jwt-decode";
import AuthContext from "./AuthContext";

export function AuthProvider({ children }) {

    const token = localStorage.getItem("token");

    const [user, setUser] = useState(
        token ? jwtDecode(token) : null
    );

    const login = (jwt) => {
        localStorage.setItem("token", jwt);
        setUser(jwtDecode(jwt));
    };

    const logout = () => {
        localStorage.removeItem("token");
        setUser(null);
    };

    return (
        <AuthContext.Provider
            value={{
                token: localStorage.getItem("token"),
                user,
                login,
                logout
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}