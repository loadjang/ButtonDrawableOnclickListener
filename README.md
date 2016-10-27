버튼 drawable 클릭 이벤트 리스너   



CButtonDrawableClick button = (CButtonDrawableClick)findViewById(R.id.button);

        button.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher,0,R.mipmap.ic_launcher,0);

        button.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {

                switch(target){

                    case RIGHT:
                        Toast.makeText(getApplicationContext(),"RIGHT",Toast.LENGTH_SHORT).show();
                        break;
                    case LEFT:
                        Toast.makeText(getApplicationContext(),"LEFT",Toast.LENGTH_SHORT).show();
                        break;
                    case OTHER:
                        Toast.makeText(getApplicationContext(),"BUTTON",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        
        
        
        
