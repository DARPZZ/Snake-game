    package com.example.snakegamefucked;

    import javafx.scene.canvas.GraphicsContext;
    import javafx.scene.paint.Color;

    import java.util.Random;
    import java.util.Timer;
    import java.util.TimerTask;

    public class Food
    {
        private static final int MAX_VISIBLE_TIME = 5500;

        private Random random = new Random();
        private int x;
        private int y;
        private int size;
        private int squareSize;
        private Timer timer;
        private TimerTask timerTask;
        private boolean isVisible;



        private boolean isDouble;
        private boolean isSpecial;
        private boolean isDoublPoints;




        public Food(int squareSize)
        {
            this.squareSize = squareSize;
            size = squareSize;
            isVisible = false;
            isSpecial = false;
            isDouble = false;
            isDoublPoints = false;
        }

        /**
         *  This method decides the chances for each food to draw
         * @param screen
         * @param height
         * @param unitSize
         */

        public void generate(int screen, int height, int unitSize)
        {
            if (isVisible) {
                cancelTimer();
            }
            isVisible = true;
            int randomNum = random.nextInt(100);

            if (randomNum <= 20) {
                isSpecial = true;
                isDouble = false;
                isDoublPoints = false;
            } else if (randomNum <= 50) {
                isSpecial = false;
                isDouble = true;
                isDoublPoints = false;
            } else if (randomNum <=85) {
                isDouble = false;
                isSpecial = false;
                isDoublPoints = true;
            } else {
                isSpecial = false;
                isDouble = false;
                isDoublPoints = false;
            }
            x = random.nextInt(screen / unitSize) * unitSize;
            y = random.nextInt(height / unitSize) * unitSize;
            startTimer();
        }
        private void startTimer()
        {
            int visibleTime = MAX_VISIBLE_TIME;
            timer = new Timer();
            timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    isVisible = false;
                    timer.cancel();
                }
            };
            timer.schedule(timerTask, visibleTime);
        }

        /**
         * Draws each food
         * @param gc
         */

        public void draw(GraphicsContext gc)
        {
            if (isVisible) {
                if (isSpecial) {
                    gc.setFill(Color.BLUE);
                } else if (isDouble) {
                    gc.setFill(Color.ORANGE);
                } else if (isDoublPoints) {
                    gc.setFill(Color.LIGHTYELLOW);
                }
                else {
                        gc.setFill(Color.MEDIUMVIOLETRED);
                    }
                    gc.fillRect((Math.round(x / squareSize) * squareSize), Math.round(y / squareSize) * squareSize, size, size);
                }
        }

        //region get/set
        public double getX()
        {
            return x;
        }

        public double getY()
        {
            return y;
        }

        public double getSize()
        {
            return size;
        }
        public boolean isDouble()
        {
            return isDouble;
        }

        public boolean isVisible()
        {
            return isVisible;
        }
        public boolean isSnakeDouble()
        {
            return isSnakeDouble;
        }

        public void setSnakeDouble(boolean snakeDouble)
        {
            isSnakeDouble = snakeDouble;
        }

        private boolean isSnakeDouble;

        public boolean isSpecial()
        {
            return isSpecial;
        }
        public boolean isDoublPoints()
        {
            return isDoublPoints;
        }
        public void setSpecial(boolean special)
        {
            isSpecial = special;
        }

        //endregion
        public void cancelTimer()
        {
            if (timer != null) {
                timer.cancel();
            }
        }
    }
