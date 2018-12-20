function y=PerformInvert(x)
    x(x==0)=2;
    x(x==1)=0;
    x(x==2)=1;
    y=x;