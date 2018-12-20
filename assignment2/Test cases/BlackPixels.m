function y=BlackPixels(x)
    [~,n]=size(x);
    y=(n-sum(x,2))';