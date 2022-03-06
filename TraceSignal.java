package App;

import javax.swing.*;
import java.awt.*;

public class TraceSignal extends JPanel {


    private int cycles;
    double pas = 0.0001;
    double pas_ech = 0.01;
    double pas_num = 0.005;
    public static double f0 = 25;
    public static double A = 0.5;
    public static double phi = Math.PI / 6;
    public double[][] s = new double[10000][2];
    int i = 0, j = 0;
    public int D = 0;
    public int E = 0;
    public int N = 0;
    public int F = 0;
    public int B = 0;
    public int Q=0;

    static int NMAX = 128;
    static int MMAX = 8;


    private MyframeFunction Function = (x) -> getA() * (Math.sin(2 * Math.PI * getF0() * x + getPhi()));//Math.sin(x);

    public TraceSignal() {

        setCycles(4);
    }

    public void setCycles(int newCycles) {
        cycles = newCycles;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        g.drawString("(0,0)", (int) (getWidth() * 0.51), (int) (getHeight() * 0.54));
        g.drawString("-0.5", (int) (getWidth() * 0.02), (int) (getHeight() * 0.54));
        g.drawString("0.5", (int) (getWidth() * 0.96), (int) (getHeight() * 0.54));
        //g.drawString("-2\u03c0f0", (int) ( getWidth()*0.02),(int)(getHeight()*0.54));
        //g.drawString("2\u03c0f0", (int) ( getWidth()*0.96),(int)(getHeight()*0.54));

        //double pas = 0.00005;
        int oldx = xToPixel(-0.5);
        int oldy = yToPixel(Function.compte(-0.5));
        if (D == 1) {
            for (double lx = -2 * getF0() * Math.PI + getPas(); lx <= 2 * getF0() * Math.PI + getPas(); lx += getPas()) {
                int x = xToPixel(lx);
                int y = yToPixel(Function.compte(lx));


                g.drawLine(x, y, oldx, oldy);

                oldx = x;
                oldy = y;
            }
            g.drawString("" + (Math.abs(A)), getWidth() / 2, yToPixel(getA()));
        }
        if (E == 1) {
            for (double lx = -2 * getF0() * Math.PI + getPas_ech(); lx <= 2 * getF0() * Math.PI + getPas_ech(); lx += getPas_ech()) {
                int x = xToPixel(lx);
                int y = yToPixel(Function.compte(lx));

                g.drawLine(oldx, getHeight() / 2, oldx, oldy);

                oldx = x;
                oldy = y;
            }


        }

        if(N==1)
        {
            for(double lx=-2*getF0()*Math.PI+getPas_ech();lx <= 2 * getF0() * Math.PI + getPas_ech(); lx += getPas_num())
            {
                int x = xToPixel(lx);
                int y = yToPixel(Function.compte(lx));
                int i = -oldx + x;
                int j = y - oldy;
                g.drawLine(oldx + i, oldy + j, oldx + i, oldy);

                g.drawLine(oldx, oldy, oldx + i, oldy);

                g.drawLine(oldx, getHeight() / 2, oldx, oldy);
                oldx = x;
                oldy = y;
            }
        }

        if (Q == 1) {

            for (double lx = -2 * getF0() * Math.PI + getPas_ech(); lx <= 2 * getF0() * Math.PI + getPas_ech(); lx += getPas_num()) {
                int x = xToPixel(lx);
                int y = yToPixel(Function.compte(lx));
                int i = -oldx + x;
                int j = y - oldy;
                g.drawLine(oldx + i, oldy + j, oldx + i, oldy);

                g.drawLine(oldx, oldy, oldx + i, oldy);
                oldx = x;
                oldy = y;
            }
        }

        if (F == 1) {
            int n = NMAX;
            float X;
            double[][] av = new double[NMAX][2];
            float h = (float) (1.0 / (n - 1));
            for (int i = 0; i < n; i++) {
                X = h * i;
                //fr[i] = x*(1-x);
                av[i][0] = Function.compte(X);
                av[i][1] = X;
            }
            double[][] ap = new double[NMAX][2];
            ap = fft(av);
            oldx = XToPixel(-n*(1/(ap.length*h))/2);
            oldy = YToPixel(0);
            for (int i = 1; i < n-1 ; i++) {
                double f=i*(1/(ap.length*h));
                int x = XToPixel(f-(n*(1/(ap.length*h)))/2);
                int y = YToPixel(Math.sqrt(Math.pow(Math.abs(ap[i][0]),2)+Math.pow(Math.abs(ap[i][1]),2)));
                g.drawLine(x, y, oldx, oldy);
                //System.out.println("   "+x+"   "+y+"   "+x+"   "+f+"   "+ap[i][0]+"   "+Math.abs(ap[i][1])+"   "+Math.sqrt(Math.pow(Math.abs(ap[i][0]),2)+Math.pow(Math.abs(ap[i][1]),2))+"   ");
                oldx = x;
                oldy = y;
            }

        }


        if (B == 1) {
            for (double lx = -2 * getF0() * Math.PI + getPas(); lx <= 2 * getF0() * Math.PI + getPas(); lx += 10 * getPas()) {
                float a = (float) (Math.random() * (0.5 - 0.000) + 0.000);
                int x = xToPixel(lx);
                int y = yToPixel((getA() + a) * Math.sin(2 * getF0() * Math.PI * lx + phi));

                g.drawLine(x, y, oldx, oldy);

                oldx = x;
                oldy = y;
            }
            //getA() * ((0.5 * (Math.random() - Math.random())) + 0.5 * (0.5 * Math.sin(getF0() * x)));
        }


    }


    static double[][] fft(double[][] s) {
        /* transformee de Fourier rapide */

        int N = s.length; /* longueur du signal : doit etre une puissance de 2 */
        if (N == 1) return s; /* fin des appels recursifs */
        int Ndiv2 = N / 2;
        double[][] fs = new double[N][2]; /* la transformee de Fourier de f */

        /* on coupe le signal en termes d'indices pairs et impairs */
        double[][] pair = new double[Ndiv2][2];
        double[][] impair = new double[Ndiv2][2];
        for (int k = 0; k < Ndiv2; k++) {
            pair[k] = s[2 * k];
            impair[k] = s[2 * k + 1];
        }

        /* calcul recursif des transformees de Fourier */
        pair = fft(pair);
        impair = fft(impair);

        /* reconstruction */
        for (int k = 0; k < N; k++) {
            fs[k][0] = pair[k % Ndiv2][0] + impair[k % Ndiv2][0] * Math.cos(2 * Math.PI * k / N) + impair[k % Ndiv2][1] * Math.sin(2 * Math.PI * k / N);
            fs[k][1] = pair[k % Ndiv2][1] + impair[k % Ndiv2][1] * Math.cos(2 * Math.PI * k / N) - impair[k % Ndiv2][0] * Math.sin(2 * Math.PI * k / N);
        }

        return fs;
    }


    public MyframeFunction getFunction() {
        return Function;
    }

    public void setFunction(MyframeFunction function) {
        Function = function;
        this.repaint();
    }

    private int XToPixel(double x) {
        return (int) (getWidth() * (x + 250) / (2 * 250));
    }

    private int YToPixel(double y) {
        return (int) (getHeight() * (1 - (y + 100) / (2.0*100)));
    }

    private int xToPixel(double x) {
        return (int) (getWidth() * (x + 0.5) / (2 * 0.5));
    }

    private int yToPixel(double y) {
        return (int) (getHeight() * (1- (y + 2) / (2.0*2)));
    }

    public static interface MyframeFunction {
        public double compte(double x);
    }


    public static double getF0() {
        return f0;
    }

    public static void setF0(double f0) {
        TraceSignal.f0 = f0;
    }

    public static double getA() {
        return A;
    }

    public static void setA(double a) {
        A = a;
    }

    public static double getPhi() {
        return phi;
    }

    public static void setPhi(double phi) {
        TraceSignal.phi = phi;
    }

    public double getPas() {
        return pas;
    }

    public void setPas(double pas) {
        this.pas = pas;
    }

    public double getPas_ech() {
        return pas_ech;
    }

    public void setPas_ech(double pas_ech) {
        this.pas_ech = pas_ech;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getF() {
        return F;
    }

    public void setF(int f) {
        F = f;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getQ() {
        return Q;
    }

    public void setQ(int q) {
        Q = q;
    }

    public double getPas_num() {
        return pas_num;
    }

    public void setPas_num(double pas_num) {
        this.pas_num = pas_num;
    }
}