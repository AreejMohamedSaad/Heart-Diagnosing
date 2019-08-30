
function main
clc; clear; close all; warning off all;
%rgb1 = imread('obyek.jpg');
%rgb1 = imread('ecgbrother1600.jpg');
%hasil_crop = imcrop(rgb1); %hasil crop disimpan


fff='C:\Users\Areej\Desktop\imagename.txt';

%fo=fopen(fff,'r');
%disp(fff);

%I'd use fgetl() to read a line, then use sscanf() or textscan() to parse the line.

  %  hasil_crop = imread (tline)

%fclose(fo);

filetext = fileread('C:\Users\Areej\Desktop\imagename.txt');
disp(filetext)



hasil_crop = imread ('datatest\image5.jpg')
%imwrite(hasil_crop,'hasil_crop.jpg');
[BW,rgb] = createMaskbr(hasil_crop);

bw4 = bwareaopen(BW,1260);
figure, imshow(hasil_crop);


imbwaa=flip(bw4 ,1); %# vertical flip 
[ k ] = thinning( imbwaa );

%run deteksi_tepi.m 
run digitasi;
baseFileName = 'my new image.png'; % Whatever....
someFolder='C:\Users\Areej\Desktop\matlaab imgs'
fullFileName = fullfile(someFolder, baseFileName);
imwrite(k, fullFileName);
end