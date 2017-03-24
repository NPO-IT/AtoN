import javax.swing.*;

/**
 * Created by DmitriX on 15.11.2016.
 */
public class Calcker {
    //номер первого слова в массиве группы
    private int numOutElemG=1;
    //смещение до след. слова
    private int stepOutGins=1;
    private int masgMax;

    public void outToTextArea(int numOutElemG,int stepOutGins){
        final int OUTSTEP=10;
        final int MAXGROUPINCIKL=32;
        final int MAXCIKLINKADR=4;
        int i,j=0,m;
        String str;
        //номер группы
        int iGr;
        //первый вычесленный номер группы
        int iGrF;
        //номер цикла
        int iC;
        //для нумерации с 0 переданный номер точки с 1 уменьшаем на 1
        i=numOutElemG-1;
        //производим вывод для 1 кадра 4 цикла по 32 группы
        //вычисляем номер первой группы
        iGr=(int)Math.floor((i+1)/masgMax);
        //iGrF=iGr;
        //вычисляем номер первого цикла
        iC=(int)Math.floor(iGr/MAXGROUPINCIKL);
        //вычисляем номер точки в первой группе
        i=((i+1)-(iGr*masgMax))-1;
        iGr++;
        iGrF=iGr;
        iC++;
        m=i;
        if (iC<=MAXCIKLINKADR){
            while (iC<=MAXCIKLINKADR){
                if (iGr<=MAXGROUPINCIKL){
                    //ограничиваем вывод циклом(32гр.). Нумерация группы с 1.
                    while (iGr<=MAXGROUPINCIKL){
                        i=m;//m
                        //вывод в пределах текущей группы
                        //вывод в пределах текущей группы
                        while (i<masgMax){
                            j++;
                            GUI.jTarea.append("Цикл №"+Integer.toString(iC)+" || "+"Группа №"+Integer.toString(iGr)+
                                    " || "+"№слова при выводе:"+Integer.toString(j)+" || "+"№слова в группе:"+Integer.toString(i)+"\n");
                            i=i+stepOutGins;
                        }
                        //j:=0;
                        //переход на след. группу
                        if (stepOutGins>masgMax){
                            iGr=iGr+((int)Math.floor(stepOutGins/masgMax));
                        }else{
                            iGr++;
                        }
                    }
                    iC=iC+(int)Math.floor(iGr/MAXGROUPINCIKL);
                    iGr=iGrF;
                }else{
                    GUI.jTarea.append("Неверный адрес");
                }
            }
        }else{
            GUI.jTarea.append("Неверный адрес");
        }
        GUI.freqJl.setText("Частота введенного параметра: "+Integer.toString(j)+" Гц");
    }

    public void culk(String adrS){
        switch (adrAnalizer(adrS)){
            case -1:
                GUI.jTarea.append("Неверный адрес! Проверьте");
                break;
            default:
                outToTextArea(numOutElemG,stepOutGins);

        }
    }

    public int adrAnalizer(String adrS){
        //переменная для хранения ASCII-кода символа
        int codAsciiGraph;
        int stepKoef;
        //int stepOutGins=1;
        int offset=0;
        //начально смещ. в массиве, зависит от П1 или П2
        int pBeginOffset=0;
        //фазы для вычисления адреса
        //Fa=8, если K=0; Fa=4, если K=1; Fa=2, если K=2; аналогично для других
        int Fa=0,Fb=0,Fc=0,Fd=0,Fe=0,Fx=0;
        //Множители для вычисления координат
        int Ma,Mb,Mc,Md,Me,Mx; //Ma=N1-1;Mb=N2-1;Mc=N3-1; и т.д
        //флаг нахождения литеры M
        boolean flagM=false;
        //Объявление для графиков
        int iGraph=0;
        boolean flagBegin=false;
        int infNum=-1;
        boolean isOkAdr=true;
        //установка в 1 для сброса ошибки
        //int numOutElemG=1;

        while (iGraph<=adrS.length()){
            //первый символ должен быть обязательно М
            if (adrS.charAt(iGraph)=='M'){
                //М есть
                flagM=true;
            }
            String infStr=new String();
            if((adrS.charAt(iGraph+1)=='1')&&(adrS.charAt(iGraph+2)=='6')){
                infStr=adrS.substring(iGraph+1,iGraph+3);
            }else {
                infStr=adrS.substring(iGraph+1,iGraph+2);
            }

            infNum=Integer.parseInt(infStr);
            if ((flagM)&&((infNum==16)||(infNum==8)||(infNum==4)||(infNum==2)||(infNum==1))){
                String thStr=new String();
                if (infNum==16){
                    //М16
                    thStr=adrS.substring(iGraph+3,iGraph+5);
                    //проверяем номер потока чтобы задать начальное смещение
                    if ((thStr.equals("П1"))||(thStr.equals("п1"))){
                        pBeginOffset=1;
                    };
                    if ((thStr.equals("П2"))||(thStr.equals("п2"))){
                        pBeginOffset=2;
                    };
                    flagBegin=true;
                    iGraph=iGraph+5;
                }else{
                    //остальные
                    //thStr=adrS.substring(iGraph+2,iGraph+3);

//                    if ((thStr.equals("П"))||(thStr.equals("П"))){
//                        flagBegin = false;
//                        break;
//                    }
                    flagBegin=true;
                    iGraph=iGraph+2;
                }
                break;
            }else{
                return -1;
            }
        }

        if(flagBegin){
            //обязательную часть проверили
            while (iGraph<adrS.length()){
                codAsciiGraph=adrS.codePointAt(iGraph);
                //вычисляем смещение и шаг слова в массиве группы
                switch (codAsciiGraph){
                    case 65:
                    case 97:
                        //Поиск А(a)
                        //проверка переданных параметров
                        if (!checkParam(adrS,iGraph)){
                            return -1;
                        }
                        Ma=Integer.parseInt(adrS.substring(iGraph+1,iGraph+2));

                        switch (infNum){
                            case 16:
                                masgMax=2048;
                                if ((Ma<1)||(Ma>8)) {
                                    //ошибка разбора адреса. неверный адрес
                                    isOkAdr=false;
                                }
                                break;
                            case 8:
                                masgMax=1024;
                                if ((Ma<1)||(Ma>8)) {
                                    //ошибка разбора адреса. неверный адрес
                                    isOkAdr=false;
                                }
                                break;
                            case 4:
                                masgMax=512;
                                if ((Ma<1)||(Ma>4)) {
                                    //ошибка разбора адреса. неверный адрес
                                    isOkAdr=false;
                                }
                                break;
                            case 2:
                                masgMax=256;
                                if ((Ma<1)||(Ma>2)) {
                                    //ошибка разбора адреса. неверный адрес
                                    isOkAdr=false;
                                }
                                break;
                            default:
                                masgMax=128;
                                if (Ma!=1){
                                    isOkAdr=false;
                                }
                        }

                        Ma--;

                        stepKoef=Integer.parseInt(adrS.substring(iGraph+2,iGraph+3));

                        switch (stepKoef){
                            case 0:
                                switch (infNum){
                                    case 16:
                                        Fa=8;
                                        break;
                                    case 8:
                                        Fa=8;
                                        break;
                                    case 4:
                                        Fa=4;
                                        break;
                                    case 2:
                                        Fa=2;
                                        break;
                                    default:
                                        Fa=1;
                                }
                                break;
                            case 1:
                                switch (infNum){
                                    case 16:
                                        Fa=4;
                                        break;
                                    case 8:
                                        Fa=4;
                                        break;
                                    case 4:
                                        Fa=2;
                                        break;
                                    case 2:
                                        Fa=1;
                                        break;
                                    default:
                                        Fa=0;
                                        isOkAdr=false;
                                }
                                break;
                            default:
                                switch (infNum){
                                    case 16:
                                        Fa=2;
                                        break;
                                    case 8:
                                        Fa=2;
                                        break;
                                    case 4:
                                        Fa=1;
                                        break;
                                    case 2:
                                        Fa=0;
                                        isOkAdr=false;
                                        break;
                                    default:
                                        Fa=0;
                                        isOkAdr=false;
                                }

                        }
                        stepOutGins=Fa;
                        offset=offset+Ma;
                        break;
                    case 66:
                    case 98:
                        //Поиск B(b)
                        //проверка переданных параметров
                        if (!checkParam(adrS,iGraph)){
                            return -1;
                        }
                        Mb=Integer.parseInt(adrS.substring(iGraph+1,iGraph+2));
                        if ((Mb<1)||(Mb>8)){
                            //ошибка разбора адреса
                            isOkAdr=false;
                        }
                        Mb--;
                        stepKoef=Integer.parseInt(adrS.substring(iGraph+2,iGraph+3));
                        switch (stepKoef){
                            case 0:
                                Fb=8;
                                break;
                            case 1:
                                Fb=4;
                                break;
                            default:
                                Fb=2;
                        }
                        offset=offset+Mb*stepOutGins;
                        stepOutGins=stepOutGins*Fb;
                        break;
                    case 67:
                    case 99:
                        //Поиск C(c)
                        //проверка переданных параметров
                        if (!checkParam(adrS,iGraph)){
                            return -1;
                        }
                        Mc=Integer.parseInt(adrS.substring(iGraph+1,iGraph+2));
                        if ((Mc<1)||(Mc>8)){
                            //ошибка разбора адреса
                            isOkAdr=false;
                        }
                        Mc--;
                        stepKoef=Integer.parseInt(adrS.substring(iGraph+2,iGraph+3));
                        switch (stepKoef){
                            case 0:
                                Fc=8;
                                break;
                            case 1:
                                Fc=4;
                                break;
                            default:
                                Fc=2;
                        }
                        offset=offset+Mc*stepOutGins;
                        stepOutGins=stepOutGins*Fc;
                        break;
                    case 68:
                    case 100:
                        //Поиск D(d)
                        //проверка переданных параметров
                        if (!checkParam(adrS,iGraph)){
                            return -1;
                        }
                        Md=Integer.parseInt(adrS.substring(iGraph+1,iGraph+2));
                        if ((Md<1)||(Md>8)){
                            //ошибка разбора адреса
                            isOkAdr=false;
                        }
                        Md--;
                        stepKoef=Integer.parseInt(adrS.substring(iGraph+2,iGraph+3));
                        switch (stepKoef){
                            case 0:
                                Fd=8;
                                break;
                            case 1:
                                Fd=4;
                                break;
                            default:
                                Fd=2;
                        }
                        offset=offset+Md*stepOutGins;
                        stepOutGins=stepOutGins*Fd;
                        break;
                    case 69:
                    case 101:
                        //Поиск E(e)
                        //проверка переданных параметров
                        if (!checkParam(adrS,iGraph)){
                            return -1;
                        }
                        Me=Integer.parseInt(adrS.substring(iGraph+1,iGraph+2));
                        if ((Me<1)||(Me>8)){
                            //ошибка разбора адреса
                            isOkAdr=false;
                        }
                        Me--;
                        stepKoef=Integer.parseInt(adrS.substring(iGraph+2,iGraph+3));
                        switch (stepKoef){
                            case 0:
                                Fe=8;
                                break;
                            case 1:
                                Fe=4;
                                break;
                            default:
                                Fe=2;
                        }
                        offset=offset+Me*stepOutGins;
                        stepOutGins=stepOutGins*Fe;
                        break;
                    case 88:
                    case 120:
                        //Поиск X(x)
                        //проверка переданных параметров
                        if (!checkParam(adrS,iGraph)){
                            return -1;
                        }
                        Mx=Integer.parseInt(adrS.substring(iGraph+1,iGraph+2));
                        if ((Mx<1)||(Mx>8)){
                            //ошибка разбора адреса
                            isOkAdr=false;
                        }
                        Mx--;
                        stepKoef=Integer.parseInt(adrS.substring(iGraph+2,iGraph+3));
                        switch (stepKoef){
                            case 0:
                                Fx=8;
                                break;
                            case 1:
                                Fx=4;
                                break;
                            default:
                                Fx=2;
                        }
                        offset=offset+Mx*stepOutGins;
                        stepOutGins=stepOutGins*Fx;
                        break;
                }
                iGraph=iGraph+3;
            }

            if (isOkAdr){
                //проверяем была ли ошибка при анализе адреса
                if (numOutElemG!=-1){
                    //N1={Ma+Mb*Fa+Mc*Fa*Fb+Md*Fa*Fb*Fc+Me*Fa*Fb*Fc*Fd+Mx*Fa*Fb*Fc*Fd*Fe}
                    //выбираем правильный первый элемент в зависимости от инф разб. адреса
                    if (infNum == 16){
                        //M16
                        numOutElemG= pBeginOffset + 2 * offset;
                        stepOutGins = 2 * stepOutGins; //T=Fa*Fb*Fc*Fd*Fe*Fx
                    }else{
                        //остальные информативности
                        numOutElemG= pBeginOffset + offset;
                        numOutElemG++;
                    }
                    return 1;
                }
            }else{
                return -1;
            }
        }else{
            return -1;
        }

        return -1;
    }

    private boolean checkParam(String adrStr,int iGraph){
        if (((adrStr.codePointAt(iGraph+1))>=48)&&((adrStr.codePointAt(iGraph+1))<=57)){
            String s=new String(adrStr.substring(iGraph+1,iGraph+2));
            if ((Integer.parseInt(s)>8)||(Integer.parseInt(s)<1)){
                return false;
            }
            s=new String(adrStr.substring(iGraph+2,iGraph+3));
            if (Integer.parseInt(s)>2){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
