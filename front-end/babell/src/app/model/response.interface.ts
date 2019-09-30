export interface ResponseInterface<T> {
  payload: T;
  status: number;
  message: string;
}
