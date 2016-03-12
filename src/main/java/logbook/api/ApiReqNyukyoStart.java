package logbook.api;

import java.util.Map;

import javax.json.JsonObject;

import logbook.bean.Ship;
import logbook.bean.ShipCollection;
import logbook.proxy.RequestMetaData;
import logbook.proxy.ResponseMetaData;

/**
 * /kcsapi/api_req_nyukyo/start
 *
 */
@API("/kcsapi/api_req_nyukyo/start")
public class ApiReqNyukyoStart implements APIListenerSpi {

    @Override
    public void accept(JsonObject json, RequestMetaData req, ResponseMetaData res) {
        if ("1".equals(req.getParameterMap().get("api_highspeed").get(0))) {
            Map<Integer, Ship> map = ShipCollection.get()
                    .getShipMap();
            Integer shipId = Integer.valueOf(req.getParameterMap()
                    .get("api_ship_id")
                    .get(0));
            Ship ship = map.get(shipId)
                    .clone();
            ship.setNowhp(ship.getMaxhp());
            ship.setNdockTime(0);
            map.put(shipId, ship);
        }
    }
}
