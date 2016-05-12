public class DLineModel extends DShapeModel {
		//DLine model already inherits x an y from DShapeModel class.
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
        
        @Override
        public int getWidth(){
        	return 0;
        }
        
        @Override 
        public int getHeight(){
        	return 0;
        }
        //returns slope of line
       public double getSlope(){
    	   //determine sign of slope.
    	   return ((double)getY2()-(double)getY())/((double)getX2()-(double)getX());
    	   
       }
        
        
        
}