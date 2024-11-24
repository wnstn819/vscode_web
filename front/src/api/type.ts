export type Employee = {
  id: number | null;
  name: string;
  password: string;
  date: Date | null;
  part: string | null;
  rank: string | null;
  role: string | null;
};

export type Login = {
  name: string;
  password: string;
};
