// src/components/navbarAdmin.tsx
import React from "react";

export const NavbarAdmin: React.FC = () => {
  return (
    <nav>
      {/* Contenido del NavbarAdmin */}
      <ul>
        <li>
          <a href="/admin/dashboard">Dashboard</a>
        </li>
        <li>
          <a href="/admin/users">Users</a>
        </li>
        <li>
          <a href="/admin/settings">Settings</a>
        </li>
      </ul>
    </nav>
  );
};
