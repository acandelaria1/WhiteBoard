public class DLineModel extends DShapeModel {
	private int x2;
        private int y2;
        
        public DLineModel(){
            x2 = 0;
            y2 = 0;
        }
        
        public int getX2(){
            return x2;
        }
        
        public int getY2(){
            return y2;
        }
        
        public void setX2(int newX2){
            x2 = newX2;
        }
        
        public void setY2(int newY2){
            y2 = newY2;
        }
}