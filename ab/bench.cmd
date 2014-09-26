delete *_*.txt
CLS
@ECHO OFF
for %%I in (10,20,30,50,75,100,125,150,175,200,225,250,275,300,325,350) DO (
ab.exe -c %%I -n 20000 -B 127.0.0.1 -k http://127.0.0.1:7101/chitchat/latest/latest/Iron%%20Man > %%I_latest.txt
ab.exe -c %%I -n 20000 -B 127.0.0.1 -k http://127.0.0.1:7101/chitchat/thread/5001 > %%I_thread.txt
ab.exe -c %%I -n 20000 -B 127.0.0.1 -k http://127.0.0.1:7101/chitchat/search/search?q=mankind > %%I_search.txt
)