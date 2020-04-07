import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({providedIn: 'root'})
export class OrderService {

  constructor(private readonly http: HttpClient) {
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`/api/orders/${id}`);
  }
}

export class Order {
  id: number;
  name: string;
  description: string;
  date: string;
}
