# -*- coding: utf-8 -*-
from libs.textrank.textrank import TextRank, RawSummarizerReader

def summarizing(contents):

    tr = TextRank()
    print('Load...')

    from konlpy.tag import Komoran
    tagger = Komoran()
    stopword = set([('있', 'VV'), ('하', 'VV'), ('되', 'VV') ])
    tr.loadSents(RawSummarizerReader(contents), lambda sent: filter(lambda x:x not in stopword and x[1] in ('NNG', 'NNP', 'VV', 'VA'), tagger.pos(sent)))
    print('Build...')
    tr.build()

    result = tr.summarize(0.15)
    result = result.replace("\n","")
    print(result)
    return result #전체 내용 요약.. ratio수치 변경으로 정도 조절

# contents =
# ['''영국 전문가들이 지금까지 개발된 백신으로 대응할 수 없는 변이 코로나 바이러스 출현 가능성을 우려하며 정부에 새로운 방역전략 수립을 촉구했다.영국 가디언은 15일(현지시각) 영국의 코로나19 비상사태에 관한
# 과학자문그룹(Sage)이 최근 백신의 효과를 무력화하는 변이 바이러스의 출현 가능성에 대해 현실성이 있다는 취지의 보고서를 작성했다고 전했다.자문그룹의 일원인 그래이엄 메들리 런던 위생·열대의학대학원 교수는
# 변이 바이러스를 아예 새로운 바이러스가 출현한 것으로 인식하고 대응해야 한다며 변이에 대응하는 백신은 상대적으로 빠르게 생산할 수 있지만, 현재 면역수준에 얼마나 영향을 미치는지에 따라 상황을 1년 전으로
# 되돌릴 수 있다고 강조했다.영국 임페리얼 칼리지 코로나19 위기대응팀 소속 마크 베글린 박사는 지금까지 경험했던 것보다 훨씬 큰 확산세로 이어질 수 있는 변이 바이러스의 유입을 막는 것이 매우 중요하며 새로운
# 변이에 대항할 수 있는 백신 업데이트가 필요하다고 조언했다.베글린 박사는 (백신 업데이트는) 몇 달이 걸릴 수 있고, 심각한 공중보건상 위협이 있는 경우 봉쇄조치를 다시 시작해야 할 수도 있다며 봉쇄조치의 강도는
# 정치적인 결정이 될 것이며, 그 강도는 현재 개발돼 있는 백신이 변이 바이러스에 얼마나 효과가 있는지에 따라 달라질 수 있다고 덧붙였다.아스트라제네카 백신 임상시험을 이끈 앤드루 폴러드 옥스퍼드대 교수는 지난
# 10일 영국 의회 내 초당파 의원 모임에 참석해 집단면역이 가능하지 않은 상황으로 보이며, 전염력이 높은 델타 변이가 퍼지고 있어 백신으로 확산을 완전히 멈추진 못할 것이라고 밝힌 바 있다.전문가들의 경고는 현재
# 영국 정부가 취하고 있는 방역 정책에 대한 비판으로도 읽힌다. 영국 정부는 16일부터 코로나19 백신 접종을 마친 사람과 18세 미만 인구에 대해 코로나19 확진자와 밀접 접촉을 한 경우에도 자가격리 의무를 부여하지
# 않기로 결정하는 등 방역조치 완화 기조를 이어가고 있다. 동시에 코로나19 확진자는 하루 3만명 수준으로 증가했고, 100명 내외의 사망자가 발생하고 있다.보리스 존슨 총리의 전 수석보좌관인 도미니크 커밍스는 변이
# 바이러스로부터 벗어날 백신 비상계획을 발표해야 하며, 국회의원들이 내각에 이같은 방안을 강제하는 방법을 찾아야 한다고 강조했다. 폴러드 교수 역시 집단면역이 아닌 중증 치료에 집중하는 방역 정책으로의 전환
# 필요성을 언급했다.세인트루이스대 심리학과 교수인 스테판 라이처 교수는 장기적으로 우리를 보호하기 위해 우리가 무엇을 잘해왔고, 잘못해왔는지 체계적인 조사가 필요하다며 팬데믹은 우리 사회의 많은 결함을 드러냈고,
#  이제 더 이상 모르는 척할 수 없다고 말했다.''']

# summarizing(contents)
