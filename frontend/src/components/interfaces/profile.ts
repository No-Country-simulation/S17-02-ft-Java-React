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
    password: string;
    roles: Role[];
}

export interface Profile {
    profileId?: string;
    profileName: string;
    profileLastname: string;
    documentType: string;
    documentNumber: string;
    avatarUrl: string;
    birth: string; // Date in ISO format
    address: string;
    district: District;
    user: User;
}
