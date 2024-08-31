export interface District {
    districtId: number;
    districtName: string;
}

export interface Role {
    roleId: number;
    roleName: string;
    roleDescription: string;
}

export interface User {
    userId: string;
    username: string;
    password: string; // normalmente no se incluriría la contraseña en las respuestas de la API por seguridad.
    roles: Role[];
}

export interface Profile {
    profileId?: string;
    profileName: string;
    profileLastname: string;
    avatarUrl: string;
    birth: string; // fecha en ISO format
    address: string;
    district: District;
    user: User;
}