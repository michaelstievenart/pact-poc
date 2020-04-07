import {TestBed} from "@angular/core/testing";
import {HttpClientModule} from "@angular/common/http";
import {Order, OrderService} from "./order.service";
import * as path from "path";
import {Matchers, Pact} from "@pact-foundation/pact";

describe('Order Service Pact Test', () => {
  const logDirectory: string = path.resolve(process.cwd(), 'pact', 'logs', 'order-service-pact.log');
  const pactOutputDirectory: string = path.resolve(process.cwd(), '..', 'pacts');

  let orderService: OrderService;
  const provider: Pact = new Pact({
    log: logDirectory,
    dir: pactOutputDirectory,
    logLevel: 'debug',
    port: 1234,
    spec: 1,
    consumer: 'PactPocApplication',
    provider: 'orderService'
  });

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        OrderService
      ]
    });

    orderService = TestBed.inject(OrderService);
  });

  beforeAll(async () => {
    await provider.setup();
  });

  afterEach(async () => {
    await provider.verify();
  });

  afterAll(async () => {
    await provider.finalize();
  });

  describe('getOrderById()', () => {

    const id = 1;

    const expected: Order = {
      id: 1,
      name: 'order 1',
      description: 'order 1 description',
      date: '2019-01-01'
    } as Order;

    beforeAll(async () => {
      await provider.addInteraction({
        state: `order 1 exists`,
        uponReceiving: 'a request to GET an order by id',
        withRequest: {
          method: 'GET',
          path: `/api/orders/${id}`
        },
        willRespondWith: {
          status: 200,
          body: Matchers.like(expected)
        }
      });
    });

    it('should get a order by id', async () => {

      await orderService.getOrderById(id).toPromise().then(response => {
        expect(response).toEqual(expected);
      });
    });
  });
});
