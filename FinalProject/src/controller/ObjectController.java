package controller;

import java.util.*;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
public class ObjectController {
    private static ObjectController oc;
    static{
        oc = new ObjectController();
    }
    private GameMap gameMap;
    private HashMap<String, List<SuperObject>> map;
    private HashMap<String, Integer> priority;
    
    public ObjectController() {
       //System.out.println("new2");
        init();
    }

    public void init(){
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        if (windowsize == null || windowsize.isEmpty()) {
            throw new IllegalStateException("Window size configuration is missing.");
        }
        gameMap = new GameMap(Integer.parseInt(windowsize.get(0)), Integer.parseInt(windowsize.get(1)));
        map = new HashMap<>();
        priority = new HashMap<>();
        map.put("floor", new ArrayList<SuperObject>());
        priority.put("floor", -1);
        
    }
    public static ObjectController getObjController(){
        return oc;
    }

    public HashMap<String, List<SuperObject>> getMap(){
        return map;
    }

    public Comparator<String> getPriorty(){
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int p1 = priority.get(o1);
                int p2 = priority.get(o2);
                if(p1>p2){
                    return 1;
                }else if(p1<p2){
                    return -1;
                }else {
                    return 0;
                }
            }
        };
    }
    public void loadMap(){
        gameMap.createMap();
    }

    /*public void gameClean(){
        ObjectController.getObjController().getMap().get("player").clear();
        ObjectController.getObjController().getMap().get("floor").clear();
        ObjectController.getObjController().getMap().get("obstacle").clear();
        ObjectController.getObjController().getMap().get("fragility").clear();
        ObjectController.getObjController().getMap().get("gameprops").clear();
        ObjectController.getObjController().getMap().get("bubble").clear();
        ObjectController.getObjController().getMap().get("bubbleExplode").clear();
    }*/
}
