export interface TodoInterface {
  id?: number;
  name: string;
  description: string;
  estimation?: number;
  inProgress: boolean;
  done?: boolean;
}
