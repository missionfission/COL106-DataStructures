function z=PerformOr(x,y)
    [m,n]=size(x);
    z=ones(m,n);
    for i=1:m
        for j=1:n
            if x(i,j)==0 && y(i,j)==0
                z(i,j)=0;
            end
        end
    end